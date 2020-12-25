import {colors} from '@atlaskit/theme';
import * as d3 from 'd3';

export const basicTypes = ['R', 'Y', 'G', 'B', 'P', 'T'];

export const colorScale = (baseColor) => d3.scaleLinear()
    .domain([0, 1])
    .range([colors[`${baseColor}100`], colors[`${baseColor}500`]]);


