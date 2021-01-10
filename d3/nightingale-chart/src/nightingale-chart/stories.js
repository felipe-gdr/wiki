import React from "react";
import { storiesOf } from "@storybook/react";
import { action } from "@storybook/addon-actions";
import { sampleData, maxedOutData } from "../data";

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

storiesOf("Nightgale chart | High level", module)
  .add("Sample data", () => (
    <Chart data={sampleData} {...defaultProps} mode="HIGH_LEVEL" />
  ))
  .add("100%", () => (
    <Chart data={maxedOutData} {...defaultProps} mode="HIGH_LEVEL" />
  ));

storiesOf("Nightgale chart | Specifics", module)
  .add("Sample data", () => (
    <Chart data={sampleData} {...defaultProps} mode="SPECIFICS" />
  ))
  .add("100%", () => (
    <Chart data={maxedOutData} {...defaultProps} mode="SPECIFICS" />
  ));
