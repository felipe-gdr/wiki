import React, {Suspense} from 'react';
import './App.css';
import { graphql } from 'babel-plugin-relay/macro';
import {loadQuery, RelayEnvironmentProvider, usePreloadedQuery,} from 'react-relay/hooks';
import RelayEnvironment from './RelayEnvironment';
import type { AppIssuesQuery } from './__generated__/AppIssuesQuery.graphql'

// Define a query
const IssuesQuery = graphql`
    query AppIssuesQuery {
        issues {
            title 
        }
    }
`;

// Immediately load the query as our app starts. For a real app, we'd move this
// into our routing configuration, preloading data as we transition to new routes.
const preloadedQuery = loadQuery(RelayEnvironment, IssuesQuery, {
    /* query variables */
});

// Inner component that reads the preloaded query results via `usePreloadedQuery()`.
// This works as follows:
// - If the query has completed, it returns the results of the query.
// - If the query is still pending, it "suspends" (indicates to React that the
//   component isn't ready to render yet). This will show the nearest <Suspense>
//   fallback.
// - If the query failed, it throws the failure error. For simplicity we aren't
//   handling the failure case here.
function App(props: any) {
    const data = usePreloadedQuery<AppIssuesQuery>(IssuesQuery, props.preloadedQuery);

    return (
        <div className="App">
            <header className="App-header">
                {data?.issues?.map(issue => 
                    <p>{issue.title}</p>
                )}
            </header>
        </div>
    );
}

// The above component needs to know how to access the Relay environment, and we
// need to specify a fallback in case it suspends:
// - <RelayEnvironmentProvider> tells child components how to talk to the current
//   Relay Environment instance
// - <Suspense> specifies a fallback in case a child suspends.
function AppRoot(props: any) {
    return (
        <RelayEnvironmentProvider environment={RelayEnvironment}>
            <Suspense fallback={'Loading...'}>
                <App preloadedQuery={preloadedQuery}/>
            </Suspense>
        </RelayEnvironmentProvider>
    );
}

export default AppRoot;