import "./App.css";
import { Chart } from "./nightingale-chart";

function App() {
  return (
    <div className="App">
      <Chart n={24} m={12} />
    </div>
  );
}

export default App;
