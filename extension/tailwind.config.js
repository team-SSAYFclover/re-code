/** @type {import('tailwindcss').Config} */
export default {
	content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}", "./node_modules/flowbite/**/*.js"],
	theme: {
		extend: {
			colors: {
				MAIN1: "#53EDC7",
				MAIN2: "#ECFFFA",
				BLACK: "#4B4B4B",
			},
		},
	},
	plugins: [],
};
