const stocks = [
  { symbol: "Apple", price: 175, volume: 120000, sector: "Technology" },
  { symbol: "Google", price: 2850, volume: 95000, sector: "Technology" },
  { symbol: "Amazon", price: 3450, volume: 110000, sector: "E-commerce" },
  { symbol: "Facebook", price: 160, volume: 85000, sector: "Finance" },
  { symbol: "Netflix", price: 620, volume: 99000, sector: "Entertainment" }
];

let total = stocks.reduce((sum, stock) => sum + stock.price, 0);
let avgPrice = (total / stocks.length).toFixed(2);
let prices = stocks.map(s => "₱" + s.price).join(" + ");
document.getElementById("averagePrice").innerHTML = `
  Prices: ${prices} <br>
  Total = ₱${total} <br>
  Average = Total ÷ ${stocks.length} = ₱${avgPrice}
`;

let techStocks = stocks.filter(stock => stock.sector === "Technology");
let techHtml = "<ul>";
techStocks.forEach(stock => {
  techHtml += `<li>${stock.symbol} - ₱${stock.price}, Volume: ${stock.volume}</li>`;
});
techHtml += "</ul>";
document.getElementById("filteredSector").innerHTML = techHtml;

let highestVolumeStock = stocks.reduce((max, stock) =>
  stock.volume > max.volume ? stock : max, stocks[0]);
document.getElementById("highestVolume").innerHTML = `
  ${highestVolumeStock.symbol} <br>
  Price: ₱${highestVolumeStock.price} <br>
  Volume: ${highestVolumeStock.volume} <br>
  Sector: ${highestVolumeStock.sector}
`;


let groups = {
  "Low (< ₱500)": stocks.filter(stock => stock.price < 500),
  "Medium (₱500 - ₱2000)": stocks.filter(stock => stock.price >= 500 && stock.price <= 2000),
  "High (> ₱2000)": stocks.filter(stock => stock.price > 2000)
};

let groupHtml = "";
for (let range in groups) {
  groupHtml += `<b>${range}</b>: ${groups[range].map(s => s.symbol + " (₱" + s.price + ")").join(", ")} <br>`;
}
document.getElementById("groupedPrices").innerHTML = groupHtml;

function fetchNewStockData() {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve({ symbol: "Microsoft", price: 310, volume: 130000, sector: "Technology" });
    }, 2000);
  });
}

fetchNewStockData().then(newStock => {
  document.getElementById("newStockData").innerHTML = `
    Fetched New Stock: <br>
    Symbol: ${newStock.symbol} <br>
    Price: ₱${newStock.price} <br>
    Volume: ${newStock.volume} <br>
    Sector: ${newStock.sector}
  `;
});
