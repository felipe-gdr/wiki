/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ReaderFragment } from "relay-runtime";
import { FragmentRefs } from "relay-runtime";
export type issue = {
    readonly title: string | null;
    readonly description: string | null;
    readonly author: {
        readonly " $fragmentRefs": FragmentRefs<"user">;
    } | null;
    readonly " $refType": "issue";
};
export type issue$data = issue;
export type issue$key = {
    readonly " $data"?: issue$data;
    readonly " $fragmentRefs": FragmentRefs<"issue">;
};



const node: ReaderFragment = {
  "argumentDefinitions": [],
  "kind": "Fragment",
  "metadata": null,
  "name": "issue",
  "selections": [
    {
      "alias": null,
      "args": null,
      "kind": "ScalarField",
      "name": "title",
      "storageKey": null
    },
    {
      "alias": null,
      "args": null,
      "kind": "ScalarField",
      "name": "description",
      "storageKey": null
    },
    {
      "alias": null,
      "args": null,
      "concreteType": "User",
      "kind": "LinkedField",
      "name": "author",
      "plural": false,
      "selections": [
        {
          "args": null,
          "kind": "FragmentSpread",
          "name": "user"
        }
      ],
      "storageKey": null
    }
  ],
  "type": "Issue",
  "abstractKey": null
};
(node as any).hash = '524bb6d9609392e36139e82f4a73883c';
export default node;
