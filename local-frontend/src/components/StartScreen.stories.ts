import StartScreenLayout from './StartScreenLayout.vue'

export default {
	component: StartScreenLayout,
	argTypes: {
		onSelect: { action: 'selected' },
		onCreate: { action: 'created' },
	},
}

export const Default = {
	args: {
		libraries: [
			{
				id: '1',
				name: 'Family pictures',
				rootPath: '/home/user/images/family',
			},
			{
				id: '2',
				name: 'Dog pictures',
				rootPath: '/home/user/images/dogs',
			},
			{
				id: '3',
				name: 'Cat pictures',
				rootPath: '/home/user/images/cats',
			},
		],
	},
}