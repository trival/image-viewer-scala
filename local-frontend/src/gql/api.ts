import { GraphQLClient } from 'graphql-request'
import { getSdk } from './sdk'

const client = new GraphQLClient('http://localhost:8088/graphql')
export const api = getSdk(client)
