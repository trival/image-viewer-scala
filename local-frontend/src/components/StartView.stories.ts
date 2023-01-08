import StartView from './StartView.vue'

export default {
	component: StartView,
	argTypes: { onSelect: { action: 'selected' } },
}

export const Default = {
	args: {
		libraries: [
			{
				name: 'Family pictures',
				rootPath: '/home/user/images/family',
				id: '1',
			},
			{
				name: 'Dog pictures',
				rootPath: '/home/user/images/dogs',
				id: '2',
			},
			{
				name: 'Cat pictures',
				rootPath: '/home/user/images/cats',
				id: '3',
			},
		],
	},
}
