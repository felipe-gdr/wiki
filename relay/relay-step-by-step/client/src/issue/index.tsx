
import React from 'react';
import { graphql } from 'babel-plugin-relay/macro';
import {useFragment,} from 'react-relay/hooks';
import { UserComponent } from '../user'
import type { issue$key } from './__generated__/issue.graphql'


const issueFragment = graphql`
    fragment issue on Issue {
        title
        description

        author {
            ...user
        }
    }
`;

type Props = {
    issue: issue$key, 
}

export function IssueComponent(props: Props) {
    const issueData = useFragment(
        issueFragment, 
        props.issue
    );

    return (
        <>
        <h1>{issueData.title}</h1>
        <p>{issueData.description}</p>
        <UserComponent user={issueData.author} />
        </>
    );
}