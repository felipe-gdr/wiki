/* tslint:disable */
/* eslint-disable */
// @ts-nocheck

import { ConcreteRequest } from "relay-runtime";
import { FragmentRefs } from "relay-runtime";
export type AppIssueQueryVariables = {
    issueId: string;
};
export type AppIssueQueryResponse = {
    readonly issue: {
        readonly " $fragmentRefs": FragmentRefs<"issue">;
    } | null;
};
export type AppIssueQuery = {
    readonly response: AppIssueQueryResponse;
    readonly variables: AppIssueQueryVariables;
};



/*
query AppIssueQuery(
  $issueId: ID!
) {
  issue(id: $issueId) {
    ...issue
    id
  }
}

fragment issue on Issue {
  title
  description
  author {
    ...user
    id
  }
}

fragment user on User {
  id
  name
}
*/

const node: ConcreteRequest = (function(){
var v0 = [
  {
    "defaultValue": null,
    "kind": "LocalArgument",
    "name": "issueId"
  }
],
v1 = [
  {
    "kind": "Variable",
    "name": "id",
    "variableName": "issueId"
  }
],
v2 = {
  "alias": null,
  "args": null,
  "kind": "ScalarField",
  "name": "id",
  "storageKey": null
};
return {
  "fragment": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Fragment",
    "metadata": null,
    "name": "AppIssueQuery",
    "selections": [
      {
        "alias": null,
        "args": (v1/*: any*/),
        "concreteType": "Issue",
        "kind": "LinkedField",
        "name": "issue",
        "plural": false,
        "selections": [
          {
            "args": null,
            "kind": "FragmentSpread",
            "name": "issue"
          }
        ],
        "storageKey": null
      }
    ],
    "type": "Query",
    "abstractKey": null
  },
  "kind": "Request",
  "operation": {
    "argumentDefinitions": (v0/*: any*/),
    "kind": "Operation",
    "name": "AppIssueQuery",
    "selections": [
      {
        "alias": null,
        "args": (v1/*: any*/),
        "concreteType": "Issue",
        "kind": "LinkedField",
        "name": "issue",
        "plural": false,
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
              (v2/*: any*/),
              {
                "alias": null,
                "args": null,
                "kind": "ScalarField",
                "name": "name",
                "storageKey": null
              }
            ],
            "storageKey": null
          },
          (v2/*: any*/)
        ],
        "storageKey": null
      }
    ]
  },
  "params": {
    "cacheID": "eda0508d51d04b84496fcdde0cf7d4bf",
    "id": null,
    "metadata": {},
    "name": "AppIssueQuery",
    "operationKind": "query",
    "text": "query AppIssueQuery(\n  $issueId: ID!\n) {\n  issue(id: $issueId) {\n    ...issue\n    id\n  }\n}\n\nfragment issue on Issue {\n  title\n  description\n  author {\n    ...user\n    id\n  }\n}\n\nfragment user on User {\n  id\n  name\n}\n"
  }
};
})();
(node as any).hash = '7f3c64ed8d24c29c7d2f4bf0c195a975';
export default node;
