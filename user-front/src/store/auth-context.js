import React, { useState } from "react";

const AuthContext = React.createContext({
	id: undefined,
	isLoggedIn: false,
	login: (id) => {},
	logout: () => {},
});

export const AuthContextProvider = (props) => {
	const [id, setId] = useState(undefined);

	const loginHandler = (id) => {
		setId(id);
	};

	const logoutHandler = () => {
		setId(undefined);
	};

	const contextValue = {
		id: id,
		isLoggedIn: !!id,
		login: loginHandler,
		logout: logoutHandler,
	};

	return (
		<AuthContext.Provider value={contextValue}>
			{props.children}
		</AuthContext.Provider>
	);
};

export default AuthContext;
