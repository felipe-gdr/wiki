/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ConcreteRequest } from "relay-runtime";
export type AppIssuesQueryVariables = {};
export type AppIssuesQueryResponse = {
    readonly issues: ReadonlyArray<{
        readonly title: string | null;
    }> | null;
};
export type AppIssuesQuery = {
    readonly response: AppIssuesQueryResponse;
    readonly variables: AppIssuesQueryVariables;
};



/*
query AppIssuesQuery {
  issues {
    title
    id
  }
}
*/

const node: ConcreteRequest = (function(){
var v0 = {
  "alias": null,
  "args": null,
  "kind": "ScalarField",
  "name": "title",
  "storageKey": null
};
return {
  "fragment": {
    "argumentDefinitions": [],
    "kind": "Fragment",
    "metadata": null,
    "name": "AppIssuesQuery",
    "selections": [
      {
        "alias": null,
        "args": null,
        "concreteType": "Issue",
        "kind": "LinkedField",
        "name": "issues",
        "plural": true,
        "selections": [
          (v0/*: any*/)
        ],
        "storageKey": null
      }
    ],
    "type": "Query",
    "abstractKey": null
  },
  "kind": "Request",
  "operation": {
    "argumentDefinitions": [],
    "kind": "Operation",
    "name": "AppIssuesQuery",
    "selections": [
      {
        "alias": null,
        "args": null,
        "concreteType": "Issue",
        "kind": "LinkedField",
        "name": "issues",
        "plural": true,
        "selections": [
          (v0/*: any*/),
          {
            "alias": null,
            "args": null,
            "kind": "ScalarField",
            "name": "id",
            "storageKey": null
          }
        ],
        "storageKey": null
      }
    ]
  },
  "params": {
    "cacheID": "cb6ef3411189acd11b597990fdd68971",
    "id": null,
    "metadata": {},
    "name": "AppIssuesQuery",
    "operationKind": "query",
    "text": "query AppIssuesQuery {\n  issues {\n    title\n    id\n  }\n}\n"
  }
};
})();
(node as any).hash = '4309ac80ad892dbd7fb6a6345c1961b5';
export default node;
