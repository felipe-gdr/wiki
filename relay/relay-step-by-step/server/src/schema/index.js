const data = require('../data/issue-data.json');
const { makeExecutableSchema } = require('graphql-tools');
const { v1: uuid } = require('uuid');
const { GraphQLScalarType, Kind } = require('graphql');

// TODO Add an enum for Issue Type and a date Scalar
const sdl = `
  type Query {
      issues(filter: IssueFilter): [Issue!]
      comments: [Comment!]
      issue(id: ID!): Issue
  }

  type Mutation {
      addIssue(input: IssueInput!): Issue
  }

  input IssueFilter {
    text: String
    type: IssueType
  }

  type Issue {
      id: ID!
      title: String
      description: String
      type: IssueType
      comments: [Comment!]
      author: User
  }

  input IssueInput {
      title: String!
      description: String!
      type: IssueType!
      date: Date!
  }

  type Comment {
      id: ID!
      content: String
      dateAdded: Date
  }

  type User {
      id: ID!
      name: String
      avatar: String
  }

  enum IssueType {
      TASK,
      BUG,
      EPIC
  }

  scalar Date 
`;

const filterIssuesByText = text => issue =>
    !text || (
        issue.title.toUpperCase().indexOf(text.toUpperCase()) >= 0
        || issue.description.toUpperCase().indexOf(text.toUpperCase()) >= 0
    );

const filterIssuesByType = type => issue =>
    !type || issue.issue_type === type;

const isValidDate = input => !isNaN(new Date(input).getTime())

const resolvers = {
    Query: {
        issues: (_, { filter: { text, type } = {} }) =>
            data.issues
                .filter(filterIssuesByText(text))
                .filter(filterIssuesByType(type)),
        comments: () => data.comments,
        issue: (_, { id }) => data.issues.find(issue => issue.id === id)
    },
    Issue: {
        type: issue => issue.issue_type,
        comments: issue => data.comments
            .filter(comment => comment.issueId === issue.id),
        author: issue => data.users
            .find(user => user.id === issue.author_id)
    },
    Comment: {
        content: comment => comment['comment-text'],
        dateAdded: comment => comment['inserted-date'],
    },
    Mutation: {
        addIssue: (_, { input: { title, description, type, date } }) => {
            const newIssue = {
                id: uuid(),
                title,
                description,
                issue_type: type
            };

            console.log('date', date)

            data.issues.push(newIssue);

            // Returned object matches "Issue" type.
            return newIssue;
        }
    },
    IssueType: {
        TASK: 'task',
        BUG: 'bug',
        EPIC: 'epic',
    },
    Date: new GraphQLScalarType({
        name: 'Date',
        description: 'Date custom scalar type',
        parseValue(value) {
            console.log('parseValue', value)
            return new Date(value).getTime();
        },
        serialize(value) {
            console.log('serialize', value)
            return new Date(value);
        },
        parseLiteral(ast) {
            if (!isValidDate(ast.value)) {
                throw new Error(`${ast.value} has an unsupported date format`)
            }
            return new Date(ast.value)
        },
    }),
};

const schema = makeExecutableSchema({ typeDefs: sdl, resolvers });

module.exports = schema;
