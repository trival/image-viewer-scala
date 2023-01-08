<template>
	<div class="mx-auto max-w-lg">
		<h2 class="text-lg font-medium">Select a photo library</h2>
		<ul
			role="list"
			class="mt-6 divide-y divide-gray-200 border-t border-b border-gray-200"
		>
			<li v-for="item in libraries" :key="item.id">
				<div class="group relative flex items-start space-x-3 py-4">
					<div class="min-w-0 flex-1">
						<div class="text-sm font-medium">
							<button @click="onSelect(item.id)">
								<span class="absolute inset-0" aria-hidden="true" />
								{{ item.name }}
							</button>
						</div>
						<p class="text-sm text-gray-500">{{ item.rootPath }}</p>
					</div>
					<div class="flex-shrink-0 self-center">
						<ChevronRightIcon
							class="h-5 w-5 text-gray-400 group-hover:text-gray-500"
							aria-hidden="true"
						/>
					</div>
				</div>
			</li>
		</ul>
		<div class="mt-6 flex">
			<a
				href="#"
				class="text-sm font-medium text-indigo-600 hover:text-indigo-500"
			>
				Or start from an empty project
				<span aria-hidden="true"> &rarr;</span>
			</a>
		</div>
	</div>
</template>

<script setup lang="ts">
import { ChevronRightIcon } from '@heroicons/vue/20/solid'

export interface Library {
	id: string
	name: string
	rootPath: string
}

export interface Props {
	libraries: Library[]
	onSelect: (libraryId: string) => void
}

defineProps<Props>()
</script>
