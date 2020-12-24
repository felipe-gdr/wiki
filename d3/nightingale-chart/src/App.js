import { max } from "d3";
import "./App.css";
import { Chart } from "./nightingale-chart";

const randomInt = max => Math.floor(Math.random() * (max + 1));

const highestScore = 4;
const areasCount = 12;
const subAreasMax = 6;

const pascalData2 = [
  [
    [4,4,4,4],
    [4,4],
    [4,4,4],
  ]
];

const pascalData = () => {
  const result = [];

  for(let x = 0; x < areasCount; x++) {
    const subAreas = [];

    for(let y = 0; y < randomInt(subAreasMax); y++) {
      subAreas.push(randomInt(highestScore));
    }

    result.push(subAreas);
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
