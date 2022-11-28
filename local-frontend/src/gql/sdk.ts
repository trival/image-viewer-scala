import { GraphQLClient } from 'graphql-request'
import * as Dom from 'graphql-request/dist/types.dom'
import gql from 'graphql-tag'
export type Maybe<T> = T | null
export type InputMaybe<T> = Maybe<T>
export type Exact<T extends { [key: string]: unknown }> = {
	[K in keyof T]: T[K]
}
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & {
	[SubKey in K]?: Maybe<T[SubKey]>
}
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & {
	[SubKey in K]: Maybe<T[SubKey]>
}
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
	ID: string
	String: string
	Boolean: boolean
	Int: number
	Float: number
}

export type Library = {
	__typename?: 'Library'
	id: Scalars['ID']
	ignorePaths: Array<Scalars['String']>
	name: Scalars['String']
	rootPath: Scalars['String']
}

export type Queries = {
	__typename?: 'Queries'
	getLibraries: Array<Library>
	/** test query */
	test: Scalars['String']
}

export type TestQueryVariables = Exact<{ [key: string]: never }>

export type TestQuery = { __typename?: 'Queries'; test: string }

export const TestDocument = gql`
	query Test {
		test
	}
`

export type SdkFunctionWrapper = <T>(
	action: (requestHeaders?: Record<string, string>) => Promise<T>,
	operationName: string,
	operationType?: string,
) => Promise<T>

const defaultWrapper: SdkFunctionWrapper = (
	action,
	_operationName,
	_operationType,
) => action()

export function getSdk(
	client: GraphQLClient,
	withWrapper: SdkFunctionWrapper = defaultWrapper,
) {
	return {
		Test(
			variables?: TestQueryVariables,
			requestHeaders?: Dom.RequestInit['headers'],
		): Promise<TestQuery> {
			return withWrapper(
				(wrappedRequestHeaders) =>
					client.request<TestQuery>(TestDocument, variables, {
						...requestHeaders,
						...wrappedRequestHeaders,
					}),
				'Test',
				'query',
			)
		},
	}
}
export type Sdk = ReturnType<typeof getSdk>
