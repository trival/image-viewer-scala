import { GraphQLClient } from 'graphql-request';
import * as Dom from 'graphql-request/dist/types.dom';
import gql from 'graphql-tag';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
};

export type Library = {
  __typename?: 'Library';
  id: Scalars['ID'];
  ignorePaths: Array<Scalars['String']>;
  name: Scalars['String'];
  rootPath: Scalars['String'];
};

export type Mutations = {
  __typename?: 'Mutations';
  createLibrary: Library;
  deleteLibrary: Scalars['Boolean'];
  updateLibrary?: Maybe<Library>;
};


export type MutationsCreateLibraryArgs = {
  name: Scalars['String'];
  rootPath: Scalars['String'];
};


export type MutationsDeleteLibraryArgs = {
  id: Scalars['String'];
};


export type MutationsUpdateLibraryArgs = {
  id: Scalars['String'];
  ignorePaths?: InputMaybe<Array<Scalars['String']>>;
  name?: InputMaybe<Scalars['String']>;
  rootPath?: InputMaybe<Scalars['String']>;
};

export type Queries = {
  __typename?: 'Queries';
  getLibraries: Array<Library>;
  /** test query */
  test: Scalars['String'];
};

export type LibraryDataFragment = { __typename?: 'Library', id: string, name: string, rootPath: string, ignorePaths: Array<string> };

export type GetLibrariesQueryVariables = Exact<{ [key: string]: never; }>;


export type GetLibrariesQuery = { __typename?: 'Queries', getLibraries: Array<{ __typename?: 'Library', id: string, name: string, rootPath: string, ignorePaths: Array<string> }> };

export type CreateLibraryMutationVariables = Exact<{
  name: Scalars['String'];
  rootPath: Scalars['String'];
}>;


export type CreateLibraryMutation = { __typename?: 'Mutations', createLibrary: { __typename?: 'Library', id: string, name: string, rootPath: string, ignorePaths: Array<string> } };

export type UpdateLibraryMutationVariables = Exact<{
  id: Scalars['String'];
  name?: InputMaybe<Scalars['String']>;
  rootPath?: InputMaybe<Scalars['String']>;
  ignorePaths?: InputMaybe<Array<Scalars['String']> | Scalars['String']>;
}>;


export type UpdateLibraryMutation = { __typename?: 'Mutations', updateLibrary?: { __typename?: 'Library', id: string, name: string, rootPath: string, ignorePaths: Array<string> } | null };

export type DeleteLibraryMutationVariables = Exact<{
  id: Scalars['String'];
}>;


export type DeleteLibraryMutation = { __typename?: 'Mutations', deleteLibrary: boolean };

export type TestQueryVariables = Exact<{ [key: string]: never; }>;


export type TestQuery = { __typename?: 'Queries', test: string };

export const LibraryDataFragmentDoc = gql`
    fragment LibraryData on Library {
  id
  name
  rootPath
  ignorePaths
}
    `;
export const GetLibrariesDocument = gql`
    query GetLibraries {
  getLibraries {
    ...LibraryData
  }
}
    ${LibraryDataFragmentDoc}`;
export const CreateLibraryDocument = gql`
    mutation CreateLibrary($name: String!, $rootPath: String!) {
  createLibrary(name: $name, rootPath: $rootPath) {
    ...LibraryData
  }
}
    ${LibraryDataFragmentDoc}`;
export const UpdateLibraryDocument = gql`
    mutation UpdateLibrary($id: String!, $name: String, $rootPath: String, $ignorePaths: [String!]) {
  updateLibrary(
    id: $id
    name: $name
    rootPath: $rootPath
    ignorePaths: $ignorePaths
  ) {
    ...LibraryData
  }
}
    ${LibraryDataFragmentDoc}`;
export const DeleteLibraryDocument = gql`
    mutation DeleteLibrary($id: String!) {
  deleteLibrary(id: $id)
}
    `;
export const TestDocument = gql`
    query Test {
  test
}
    `;

export type SdkFunctionWrapper = <T>(action: (requestHeaders?:Record<string, string>) => Promise<T>, operationName: string, operationType?: string) => Promise<T>;


const defaultWrapper: SdkFunctionWrapper = (action, _operationName, _operationType) => action();

export function getSdk(client: GraphQLClient, withWrapper: SdkFunctionWrapper = defaultWrapper) {
  return {
    GetLibraries(variables?: GetLibrariesQueryVariables, requestHeaders?: Dom.RequestInit["headers"]): Promise<GetLibrariesQuery> {
      return withWrapper((wrappedRequestHeaders) => client.request<GetLibrariesQuery>(GetLibrariesDocument, variables, {...requestHeaders, ...wrappedRequestHeaders}), 'GetLibraries', 'query');
    },
    CreateLibrary(variables: CreateLibraryMutationVariables, requestHeaders?: Dom.RequestInit["headers"]): Promise<CreateLibraryMutation> {
      return withWrapper((wrappedRequestHeaders) => client.request<CreateLibraryMutation>(CreateLibraryDocument, variables, {...requestHeaders, ...wrappedRequestHeaders}), 'CreateLibrary', 'mutation');
    },
    UpdateLibrary(variables: UpdateLibraryMutationVariables, requestHeaders?: Dom.RequestInit["headers"]): Promise<UpdateLibraryMutation> {
      return withWrapper((wrappedRequestHeaders) => client.request<UpdateLibraryMutation>(UpdateLibraryDocument, variables, {...requestHeaders, ...wrappedRequestHeaders}), 'UpdateLibrary', 'mutation');
    },
    DeleteLibrary(variables: DeleteLibraryMutationVariables, requestHeaders?: Dom.RequestInit["headers"]): Promise<DeleteLibraryMutation> {
      return withWrapper((wrappedRequestHeaders) => client.request<DeleteLibraryMutation>(DeleteLibraryDocument, variables, {...requestHeaders, ...wrappedRequestHeaders}), 'DeleteLibrary', 'mutation');
    },
    Test(variables?: TestQueryVariables, requestHeaders?: Dom.RequestInit["headers"]): Promise<TestQuery> {
      return withWrapper((wrappedRequestHeaders) => client.request<TestQuery>(TestDocument, variables, {...requestHeaders, ...wrappedRequestHeaders}), 'Test', 'query');
    }
  };
}
export type Sdk = ReturnType<typeof getSdk>;