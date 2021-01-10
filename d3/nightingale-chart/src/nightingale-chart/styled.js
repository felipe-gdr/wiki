import styled, { css } from "styled-components";
import { colors } from "@atlaskit/theme";

export const Arc = styled.path`
  ${(props) =>
    props.selected
      ? css`
          stroke: ${colors.N800};
          stroke-width: 4px;
          stroke-linejoin: round;
        `
      : null};
`;

export const Boundaries = styled.path`
  fill: ${colors.N20};
`;
