
import React from 'react';
import { graphql } from 'babel-plugin-relay/macro';
import {useFragment,} from 'react-relay/hooks';
import type { user$key } from './__generated__/user.graphql'

const userFragment = graphql`
    fragment user on User {
        id
        name
    }
`;

type Props = {
    user: user$key | null, 
}

export function UserComponent(props: Props) {
    const userData = useFragment(
        userFragment, 
        props.user
    );

    return (
        <div>{userData?.name || 'Unknow'}</div>
    );
}