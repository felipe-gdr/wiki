import React, { Fragment } from "react";
import { colorScale, basicTypes } from "./colors";
import { Arc, Boundaries } from "./styled";
import * as d3 from "d3";

const width = 300;

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

export const Chart = ({
  data,
  onHover,
  config: { highestScore },
  selectedItem,
}) => {
  const { expectations } = data;
  const expectationsCount = expectations.length;

  return (
    <svg
      viewBox={`-${width + 4} -${width + 4} ${(width + 4) * 2} ${
        (width + 4) * 2
      }`}
      style={{ maxWidth: width }}
    >
      <Boundaries
        key="boundary"
        d={arcFn({
          startAngle: 0,
          endAngle: Math.PI * 2,
          innerRadius: 0,
          outerRadius: totalOuterRadius,
        })}
      />
      {expectations.map((expectation, i) => {
        const sliceStartAngle = (i / expectationsCount) * 2 * Math.PI;
        const sliceEndAngle = ((i + 1) / expectationsCount) * 2 * Math.PI;
        const { highLevels } = expectation;
        const highLevelsCount = highLevels.length;
        const interpolate = d3.interpolateNumber(
          sliceStartAngle,
          sliceEndAngle
        );

        return highLevels
          .map((highLevel, j) => {
            const { specifics } = highLevel;
            const startAngle = interpolate(j / highLevelsCount);
            const endAngle = interpolate((j + 1) / highLevelsCount);

            const maxDepth = specifics.length * highestScore;
            let currentLevel = 0;
            const color = colorScale(basicTypes[i])(j / expectationsCount);
            return (
              <Fragment key={j}>
                {specifics
                  .filter(({ value }) => value !== null)
                  .map((specific, k) => {
                    const { value } = specific;
                    const padding = 0.007 * width;
                    const radiusSize =
                      (totalOuterRadius - totalInnerRadius) / maxDepth;
                    const innerRadius =
                      radiusSize * currentLevel + totalInnerRadius;
                    const outerRadius = innerRadius + radiusSize * value;
                    currentLevel += value;

                    return (
                      <Arc
                        key={k}
                        selected={
                          selectedItem && specific.name === selectedItem.name
                        }
                        onMouseEnter={() => onHover(specific)}
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
