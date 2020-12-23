import React, { Fragment } from "react";
import * as d3 from "d3";

const arcFn = d3
  .arc()
  .innerRadius(({ innerRadius}) => innerRadius)
  .outerRadius(({ outerRadius}) => outerRadius)
  .startAngle(({ startAngle }) => startAngle)
  .endAngle(({ endAngle }) => endAngle);

const totalInnerRadius = 50;
const totalOuterRadius = 300;

export const Chart = ({ n, m }) => {
  const radiusSize = (totalOuterRadius - totalInnerRadius) / m;

  return (
    <svg viewBox="-320 -320 640 640" style={{ maxWidth: 640 }}>
      {Array.from({ length: n }, (_, i) => {
          const startAngle = (i / n) * 2 * Math.PI;
          const endAngle = ((i + 1) / n) * 2 * Math.PI;
          return (
              <Fragment>
                  {Array.from({ length: m}, (_, j) => {
                    const innerRadius = (radiusSize * j) + totalInnerRadius;
                    const outerRadius = innerRadius + radiusSize;

                    return <path
                      stroke="white"
                      fill={d3.interpolateRainbow(i / n)}
                      d={arcFn({ startAngle, endAngle, innerRadius, outerRadius})}
                  ></path>
                  })}
              </Fragment>
          );
      })}
    </svg>
  );
};
