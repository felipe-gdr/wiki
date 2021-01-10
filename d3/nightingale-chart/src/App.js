import { useState, useCallback, useMemo } from "react";
import "./App.css";
import { Chart } from "./nightingale-chart";
import { sampleData } from "./data";

const highestScore = 4;

function App() {
  const [item, setItem] = useState(null);
  const [mode, setMode] = useState("1");
  const onHover = useCallback((value) => setItem(value), [setItem]);
  const toggleMode = useCallback(
    () => setMode((currentValue) => (currentValue === "1" ? "2" : "1")),
    [setMode]
  );

  return (
    <div className="App">
      <button onClick={toggleMode}>toggle mode</button>
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
