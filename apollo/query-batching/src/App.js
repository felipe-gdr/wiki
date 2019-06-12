import React from 'react';
import ApolloClient from 'apollo-client';
import { ApolloProvider } from 'react-apollo';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { BatchHttpLink } from 'apollo-link-batch-http';

import Movies from './Movies';
import Books from './Books';
import './App.css';

const httpLink = new BatchHttpLink({
  uri: 'http://localhost:4000/graphql',
});

const client = new ApolloClient({
  link: httpLink,
  cache: new InMemoryCache(),
});

function App() {
  return (
    <ApolloProvider client={client}>
      <div className="App">
        <header>
          <Movies />
        </header>
        <hr />
        <div>
          <Books />
        </div>
      </div>
    </ApolloProvider>
  );
}

export default App;
