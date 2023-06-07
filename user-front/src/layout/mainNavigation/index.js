import { Link, useHistory } from "react-router-dom";
import clasess from "./MainNavigation.module.css";
import { useContext } from "react";
import AuthContext from "../../store/auth-context";

const MainNavigation = () => {
	const user = useContext(AuthContext);
	const history = useHistory();

	const logoutHandler = () => {
		user.logout();
		history.push("/auth");
	};

	return (
		<header className={clasess.header}>
			<div className={clasess.logo}>User service</div>
			<nav>
				{user.isLoggedIn && (
					<ul>
						<li>
							<Link to="/homepage">My Profile</Link>
						</li>

						<li>
							<button onClick={logoutHandler}>Logout</button>
						</li>
					</ul>
				)}
				{!user.isLoggedIn && (
					<ul>
						<li className={clasess.login}>
							<Link to="/auth">Login</Link>
						</li>
						<li className={clasess.login}>
							<Link to="/registration">Registration</Link>
						</li>
					</ul>
				)}
			</nav>
		</header>
	);
};

export default MainNavigation;
