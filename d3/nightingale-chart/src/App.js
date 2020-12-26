import { useState, useCallback, useMemo } from "react";
import "./App.css";
import { Chart } from "./nightingale-chart";
import { sampleData } from "./data";

const highestScore = 4;

function App() {
  const [item, setItem] = useState(null);
  const onHover = useCallback((value) => setItem(value), [setItem]);

  return (
    <div className="App">
      <Chart
        data={sampleData}
        config={{ highestScore }}
        onHover={onHover}
        selectedItem={item}
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
