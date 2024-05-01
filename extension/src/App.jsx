import { createMemoryRouter, RouterProvider } from "react-router-dom";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import DefaultLayout from "./layouts/DefaultLayout";
import { useEffect, useState } from "react";

function App() {
	const [loginId, setLoginId] = useState(undefined);

	useEffect(() => {
		chrome.storage.local.get("id").then((res) => {
			setLoginId(res.githubId);
		});
	}, []);

	const router = createMemoryRouter([
		{
			path: "/",
			element: <DefaultLayout />,
			children: [
				{
					index: true,
					element: loginId ? <HomePage /> : <LoginPage />,
				},
			],
		},
	]);

	return (
		<>
			<RouterProvider router={router} />
		</>
	);
}

export default App;
