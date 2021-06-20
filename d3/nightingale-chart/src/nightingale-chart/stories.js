import React from "react";
import { storiesOf } from "@storybook/react";
import { action } from "@storybook/addon-actions";
import { sampleData, maxedOutData, randomData } from "../data";

import { Chart } from "./index";

const actions = {
  onHover: action("onHover"),
};

const highestScore = 4;

const defaultProps = {
  ...actions,
  config: { highestScore },
  selectedItem: null,
};

storiesOf("Nightgale chart | High level & Specifics comparison", module)
  .add("Sample data", () => (
    <div>
      <Chart data={sampleData} {...defaultProps} mode="HIGH_LEVEL" />
      <Chart data={sampleData} {...defaultProps} mode="SPECIFICS" />
    </div>
  ))
  .add("Random", () => (
    <div>
      <Chart data={randomData} {...defaultProps} mode="HIGH_LEVEL" />
      <Chart data={randomData} {...defaultProps} mode="SPECIFICS" />
    </div>
  ))
  .add("100%", () => (
    <div>
      <Chart data={maxedOutData} {...defaultProps} mode="HIGH_LEVEL" />
      <Chart data={maxedOutData} {...defaultProps} mode="SPECIFICS" />
    </div>
  ));
