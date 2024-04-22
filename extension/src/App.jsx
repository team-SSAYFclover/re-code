import { createMemoryRouter, RouterProvider } from "react-router-dom";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import DefaultLayout from "./layouts/DefaultLayout";
import { useEffect, useState } from "react";

function App() {
	const [loginId, setLoginId] = useState(undefined);

	useEffect(() => {
		if (typeof chrome !== "undefined" && chrome.storage && chrome.storage.local) {
			chrome.storage.local.get(["loginId"], function (result) {
				setLoginId(result.loginId); // 로그인 상태 업데이트
			});
		}
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
