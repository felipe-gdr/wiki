import React, { Fragment } from "react";
import { colorScale, basicTypes } from "./colors";
import { Arc } from "./styled";
import * as d3 from "d3";

const width = 500;

const arcFn = d3
  .arc()
  .innerRadius(({ innerRadius }) => innerRadius)
  .outerRadius(({ outerRadius }) => outerRadius)
  .startAngle(({ startAngle }) => startAngle)
  .endAngle(({ endAngle }) => endAngle)
  .padAngle(Math.PI / 200)
  .padRadius(0.45 * width);

const totalInnerRadius = 20;
const totalOuterRadius = width;

export const Chart = ({ data, config: { highestScore } }) => {
  const pillarsCount = data.length;

  return (
    <svg viewBox={`-${width} -${width} ${width * 2} ${width * 2}`} style={{ maxWidth: width }}>
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
                  const padding = 4;
                  const radiusSize =
                    (totalOuterRadius - totalInnerRadius) / maxDepth;
                  const innerRadius =
                    radiusSize * currentLevel + totalInnerRadius;
                  const outerRadius = innerRadius + radiusSize * value;
                  currentLevel += value;

                  return (
                    <Arc
                      onMouseEnter={() => console.log(k)}
                      key={k}
                      fill={color}
                      d={arcFn({
                        startAngle,
                        endAngle,
                        innerRadius: innerRadius + padding,
                        outerRadius,
                      })}
                    />
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
