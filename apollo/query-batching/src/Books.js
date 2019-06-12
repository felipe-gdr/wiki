import React from 'react';

import { Query } from 'react-apollo';
import gql from 'graphql-tag';

const QUERY = gql`
  {
    books {
      title
      author
    }
  }
`;

export default class Books extends React.Component {

  render() {
    return (
      <Query query={QUERY}>
        {({ data, error, loading }) => {
          if (loading) {
            return <div>loading...</div>
          }

          if (error) {
            return <div>{error}</div>
          }

          return (
            data.books.map((book) => (
              <div key={book.title}>{book.title} - {book.author}</div>
            ))
          )
        }}

      </Query>
    )
  }
}