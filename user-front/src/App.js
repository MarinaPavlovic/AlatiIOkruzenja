import AuthForm from "./auth/AuthForm";
import Registration from "./auth/Registration";
import { Route, Switch } from "react-router-dom";
import Layout from "./layout/Layout";
import MyProfile from "./pages/MyProfile";

function App() {
	return (
		<Layout>
			<Switch>
				<Route path="/auth">
					<AuthForm />
				</Route>
				<Route path="/registration">
					<Registration />
				</Route>
				<Route path="/homepage">
					<MyProfile />
				</Route>
			</Switch>
		</Layout>
	);
}

export default App;
