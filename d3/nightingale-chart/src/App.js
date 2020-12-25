import "./App.css";
import { Chart } from "./nightingale-chart";

const randomInt = max => Math.floor(Math.random() * (max)) + 1;

const highestScore = 4;
const pillarsCount = 4;
const areasMax = 8;
const subAreasMax = 5;

const pascalData = () => {
  const result = [];

  for(let x = 0; x < pillarsCount; x++) {
    const areas = [];

    for(let y = 0; y < randomInt(areasMax); y++) {
      const subAreas = [];
      for(let y = 0; y < randomInt(subAreasMax); y++) {
        subAreas.push(randomInt(highestScore));
      }

      areas.push(subAreas)
    }

    result.push(areas);
  }

  return result;
};

function App() {
  return (
    <div className="App">
      <Chart data={pascalData()} config={{ highestScore }}/>
    </div>
  );
}

export default App;
