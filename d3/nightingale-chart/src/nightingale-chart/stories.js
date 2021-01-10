import React from "react";
import { storiesOf } from "@storybook/react";
import { action } from "@storybook/addon-actions";
import { sampleData } from "../data";

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

storiesOf("Nightgale chart", module)
  .add("High level", () => (
    <Chart data={sampleData} {...defaultProps} mode="HIGH_LEVEL" />
  ))
  .add("Specifics", () => (
    <Chart data={sampleData} {...defaultProps} mode="SPECIFICS" />
  ));
