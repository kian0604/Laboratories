const API_URL = "http://localhost:8080/api/todos";

const form = document.getElementById("todo-form");
const input = document.getElementById("todo-input");
const todoList = document.getElementById("todo-list");

async function fetchTodos() {
  const response = await fetch(API_URL);
  const todos = await response.json();
  todoList.innerHTML = "";
  todos.forEach(renderTodo);
}

function renderTodo(todo) {
  const li = document.createElement("li");
  li.className = todo.completed ? "completed" : "";

  const span = document.createElement("span");
  span.textContent = todo.title;

  span.addEventListener("click", () => toggleComplete(todo));

  const actions = document.createElement("div");
  actions.classList.add("actions");

  const editBtn = document.createElement("button");
  editBtn.textContent = "Edit";
  editBtn.classList.add("edit");
  editBtn.addEventListener("click", () => editTodo(todo));

  const deleteBtn = document.createElement("button");
  deleteBtn.textContent = "Delete";
  deleteBtn.classList.add("delete");
  deleteBtn.addEventListener("click", () => deleteTodo(todo.id));

  actions.appendChild(editBtn);
  actions.appendChild(deleteBtn);

  li.appendChild(span);
  li.appendChild(actions);
  todoList.appendChild(li);
}

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  const title = input.value.trim();
  if (!title) return;

  await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title, completed: false }),
  });

  input.value = "";
  fetchTodos();
});

async function toggleComplete(todo) {
  await fetch(`${API_URL}/${todo.id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ ...todo, completed: !todo.completed }),
  });
  fetchTodos();
}

async function editTodo(todo) {
  const newTitle = prompt("Edit task:", todo.title);
  if (newTitle !== null) {
    await fetch(`${API_URL}/${todo.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ ...todo, title: newTitle }),
    });
    fetchTodos();
  }
}

async function deleteTodo(id) {
  await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  fetchTodos();
}

fetchTodos();
