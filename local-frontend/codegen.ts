import type { CodegenConfig } from '@graphql-codegen/cli'

const config: CodegenConfig = {
	overwrite: true,
	schema: 'http://localhost:8088/graphql',
	documents: 'src/**/*.graphql',
	generates: {
		'./src/gql/sdk.ts': {
			plugins: [
				'typescript',
				'typescript-operations',
				'typescript-graphql-request',
			],
		},
		'./graphql.schema.json': {
			plugins: ['introspection'],
		},
	},
}

export default config
