import React from 'react';

import { Query } from 'react-apollo';
import gql from 'graphql-tag';

const QUERY = gql`
  {
    movies {
      title
      director
    }
  }
`;

export default class Movies extends React.Component {

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
            data.movies.map(movie => (
              <div key={movie.title}>{movie.title} - {movie.director}</div>
            ))
          )
        }}

      </Query>
    )
  }
}