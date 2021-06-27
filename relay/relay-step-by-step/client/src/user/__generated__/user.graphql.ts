/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ReaderFragment } from "relay-runtime";
import { FragmentRefs } from "relay-runtime";
export type user = {
    readonly id: string;
    readonly name: string | null;
    readonly " $refType": "user";
};
export type user$data = user;
export type user$key = {
    readonly " $data"?: user$data;
    readonly " $fragmentRefs": FragmentRefs<"user">;
};



const node: ReaderFragment = {
  "argumentDefinitions": [],
  "kind": "Fragment",
  "metadata": null,
  "name": "user",
  "selections": [
    {
      "alias": null,
      "args": null,
      "kind": "ScalarField",
      "name": "id",
      "storageKey": null
    },
    {
      "alias": null,
      "args": null,
      "kind": "ScalarField",
      "name": "name",
      "storageKey": null
    }
  ],
  "type": "User",
  "abstractKey": null
};
(node as any).hash = 'ddb41f37799851729b0e1a0845a4c127';
export default node;
