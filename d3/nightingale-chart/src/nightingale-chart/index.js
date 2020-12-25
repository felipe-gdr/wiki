import React, { Fragment } from "react";
import { colorScale, basicTypes } from "./colors";
import * as d3 from "d3";

const arcFn = d3
  .arc()
  .innerRadius(({ innerRadius }) => innerRadius)
  .outerRadius(({ outerRadius }) => outerRadius)
  .startAngle(({ startAngle }) => startAngle)
  .endAngle(({ endAngle }) => endAngle);

const totalInnerRadius = 20;
const totalOuterRadius = 300;

export const Chart = ({ data, config: { highestScore } }) => {
  const pillarsCount = data.length;

  return (
    <svg viewBox="-320 -320 640 640" style={{ maxWidth: 640 }}>
      {Array.from({ length: pillarsCount }, (_, i) => {
        const sliceStartAngle = (i / pillarsCount) * 2 * Math.PI;
        const sliceEndAngle = ((i + 1) / pillarsCount) * 2 * Math.PI;
        const areasCount = data[i].length;
        const interpolate = d3.interpolateNumber(
          sliceStartAngle,
          sliceEndAngle
        );

        return data[i]
          .map((area, j) => {
            const startAngle = interpolate(j / areasCount);
            const endAngle = interpolate((j + 1) / areasCount);

            const maxDepth = area.length * highestScore;
            let currentLevel = 0;
            const color = colorScale(basicTypes[i])(j / data[i].length);
            return (
              <Fragment key={j}>
                {area.map((value, k) => {
                  const radiusSize =
                    (totalOuterRadius - totalInnerRadius) / maxDepth;
                  const innerRadius =
                    radiusSize * currentLevel + totalInnerRadius;
                  const outerRadius = innerRadius + radiusSize * value;
                  currentLevel += value;

                //   console.log('->', k / area.length)
                //   console.log('->', colorScale(i)(k / area.length))

                  return (
                    <path
                      key={k}
                      stroke="white"
                      fill={color}
                      d={arcFn({
                        startAngle,
                        endAngle,
                        innerRadius,
                        outerRadius,
                      })}
                    ></path>
                  );
                })}
              </Fragment>
            );
          })
          .reduce((acc, item) => {
            acc.push(item);

            return acc;
          }, []);
      })}
    </svg>
  );
};
