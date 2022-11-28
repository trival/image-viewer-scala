<template>
	<div class="p-8">
		<h1 class="font-bold">First Component</h1>
		<p>Test: {{ testStore.test }}</p>
		<button @click="testStore.refreshTest">refreshTest</button>
	</div>
</template>

<script setup lang="ts">
import { useTestStore } from '@/stores/test'

const testStore = useTestStore()
</script>
