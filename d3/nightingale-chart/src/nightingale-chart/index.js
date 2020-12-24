import React, { Fragment } from "react";
import * as d3 from "d3";

const arcFn = d3
  .arc()
  .innerRadius(({ innerRadius }) => innerRadius)
  .outerRadius(({ outerRadius }) => outerRadius)
  .startAngle(({ startAngle }) => startAngle)
  .endAngle(({ endAngle }) => endAngle);

const totalInnerRadius = 50;
const totalOuterRadius = 300;

export const Chart = ({ data, config: { highestScore } }) => {
  const slices = data.length;

  return (
    <svg viewBox="-320 -320 640 640" style={{ maxWidth: 640 }}>
      {Array.from({ length: slices }, (_, i) => {
        const startAngle = (i / slices) * 2 * Math.PI;
        const endAngle = ((i + 1) / slices) * 2 * Math.PI;
        const maxDepth = data[i].length * highestScore;
        let currentLevel = 0;
        return (
          <Fragment key={i}>
            {data[i].map((value, j) => {
              const radiusSize = (totalOuterRadius - totalInnerRadius) / maxDepth;
              const innerRadius = radiusSize * currentLevel + totalInnerRadius;
              const outerRadius = innerRadius + (radiusSize * value);
              currentLevel += value;

              return (
                <path
                  key={j}
                  stroke="white"
                  fill={d3.interpolateRainbow(i / slices)}
                  d={arcFn({ startAngle, endAngle, innerRadius, outerRadius })}
                ></path>
              );
            })}
          </Fragment>
        );
      })}
    </svg>
  );
};
