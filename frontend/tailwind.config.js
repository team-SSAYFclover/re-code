import scrollbarHide from 'tailwind-scrollbar-hide';

/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        MAIN1: '#53EDC7',
        MAIN2: '#ECFFFA',
        BLACK: '#4B4B4B',
      },
      transformOrigin: {
        'bottom-center': 'center bottom',
      },
      animation: {
        wiggle: 'wiggle 7s ease-in-out infinite',
      },
      keyframes: {
        wiggle: {
          '0%, 100%': { transform: 'rotate(-5deg)' },
          '50%': { transform: 'rotate(5deg)' },
        },
      },
    },
  },
  plugins: [scrollbarHide],
};
