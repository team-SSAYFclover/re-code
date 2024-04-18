import GuidePage from '@/pages/guide/GuidePage';
import HomePage from '@/pages/home/HomePage';
import LoginPage from '@/pages/login/LoginPage';
import ProblemPage from '@/pages/problem/ProblemPage';
import RecodePage from '@/pages/recode/RecodePage';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';

const router = createBrowserRouter([
  {
    path: '/',
    element: <HomePage />,
  },
  {
    path: '/login',
    element: <LoginPage />,
  },
  {
    path: '/problem',
    element: <ProblemPage />,
  },
  {
    path: '/recode',
    element: <RecodePage />,
  },
  {
    path: '/guide',
    element: <GuidePage />,
  },
]);

const App = () => {
  return <RouterProvider router={router} />;
};

export default App;
