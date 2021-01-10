import { useState, useCallback } from "react";
import "./App.css";
import { Chart } from "./nightingale-chart";
import { sampleData } from "./data";

const highestScore = 4;

function App() {
  const [item, setItem] = useState(null);
  const [mode, setMode] = useState("HIGH_LEVEL");
  const onHover = useCallback((value) => setItem(value), [setItem]);
  const toggleMode = useCallback(
    () => setMode((currentValue) => (currentValue === "HIGH_LEVEL" ? "SPECIFICS" : "HIGH_LEVEL")),
    [setMode]
  );

  return (
    <div className="App">
      <button onClick={toggleMode}>Visualization type: {mode}</button>
      <Chart
        data={sampleData}
        config={{ highestScore }}
        onHover={onHover}
        selectedItem={item}
        mode={mode}
      />
      {item && (
        <div>
          {item.name}: {item.value}
        </div>
      )}
    </div>
  );
}

export default App;
