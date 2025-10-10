const API_URL = "http://localhost:8080/api/todos"; // change if your backend uses another endpoint

const todoList = document.getElementById("todo-list");
const addBtn = document.getElementById("add-btn");
const titleInput = document.getElementById("todo-title");

// Load todos from backend when page loads
document.addEventListener("DOMContentLoaded", getTodos);

// CREATE
addBtn.addEventListener("click", async () => {
  const title = titleInput.value.trim();
  if (title === "") {
    alert("Please enter a task!");
    return;
  }

  const newTodo = { title: title };

  await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(newTodo),
  });

  titleInput.value = "";
  getTodos();
});

// READ
async function getTodos() {
  const response = await fetch(API_URL);
  const todos = await response.json();

  todoList.innerHTML = "";
  todos.forEach((todo) => {
    const li = document.createElement("li");
    li.innerHTML = `
      <span>${todo.title}</span>
      <div>
        <button class="edit" onclick="editTodo(${todo.id}, '${todo.title}')">✏️</button>
        <button class="delete" onclick="deleteTodo(${todo.id})">🗑️</button>
      </div>
    `;
    todoList.appendChild(li);
  });
}

// UPDATE
async function editTodo(id, oldTitle) {
  const newTitle = prompt("Edit todo:", oldTitle);
  if (newTitle === null || newTitle.trim() === "") return;

  await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title: newTitle }),
  });

  getTodos();
}

// DELETE
async function deleteTodo(id) {
  if (!confirm("Are you sure you want to delete this?")) return;

  await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  getTodos();
}
