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
        modalOpen: 'FadeIn 0.3s ease-in-out',
        modalClose: 'FadeOut 0.3s ease-in-out',
        toggleUp: 'slideUp 0.2s ease-in forwards',
        toggleDown: 'slideDown 0.2s ease-out forwards',
        shake: 'shake 0.2s ease-in-out',
      },
      keyframes: {
        wiggle: {
          '0%, 100%': { transform: 'rotate(-5deg)' },
          '50%': { transform: 'rotate(5deg)' },
        },
        FadeIn: {
          from: { opacity: 0, transform: 'translate(-50%, -40%)' },
          to: { opacity: 1, transform: 'translate(-50%, -50%)' },
        },
        FadeOut: {
          from: { opacity: 1, transform: 'translate(-50%, -50%)' },
          to: { opacity: 0, transform: 'translate(-50%, -40%)' },
        },
        slideUp: {
          '0%': { transform: 'scaleY(1)', transformOrigin: 'top' },
          '100%': { transform: 'scaleY(0)', transformOrigin: 'top' },
        },
        slideDown: {
          '0%': { transform: 'scaleY(0)', transformOrigin: 'top' },
          '100%': { transform: 'scaleY(1)', transformOrigin: 'top' },
        },
        shake: {
          '0%, 100%': { transform: 'translateX(0)' },
          '25%': { transform: 'translateX(-3px)' },
          '75%': { transform: 'translateX(3px)' },
        },
      },
    },
  },
  plugins: [scrollbarHide, import('@tailwindcss/typography')],
};
