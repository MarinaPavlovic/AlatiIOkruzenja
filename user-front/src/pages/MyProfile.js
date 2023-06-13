import { useEffect, useState } from "react";
import { useContext } from "react";
import AuthContext from "../store/auth-context";
import classes from "./MyProfile.module.css";
import MessageModal from "../ui/MessageModal";
import Backdrop from "../ui/Backdrop";
import DeleteCard from "../ui/DeleteCard";
import { useHistory } from "react-router-dom";

function MyProfilePage() {
	const authCtx = useContext(AuthContext);
	const [fullName, setFullName] = useState("");
	const [username, setUsername] = useState("");
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const userId = authCtx.id;
	const [edit, setEdit] = useState(false);
	const [error, setError] = useState(false);
	const [message, setMessage] = useState(false);
	const [deleteCard, setDeleteCard] = useState(false);
	const history = useHistory();

	useEffect(() => {
		fetch("http://localhost:8080/user/" + userId)
			.then((response) => {
				return response.json();
			})
			.then((data) => {
				setFullName(data.fullName);
				setUsername(data.username);
				setEmail(data.email);
			});
	}, [userId]);

	const editStateHandler = (e) => {
		e.preventDefault();
		setEdit(true);
	};

	const editSubmitHandler = (e) => {
		e.preventDefault();
		fetch("http://localhost:8080/user/create", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify({
				id: userId,
				fullName: fullName,
				username: username,
				password: password,
				email: email,
			}),
		})
			.then((res) => {
				if (res.status >= 400) {
					setError(true);
				} else {
					setMessage(true);
				}
				return res.json();
			})
			.then((data) => {
				authCtx.logout();
				history.push("/auth");
			});
	};

	function closeError() {
		setError(false);
	}
	function closeMessage() {
		setMessage(false);
	}
	function closeDeleteCard() {
		setDeleteCard(false);
	}

	function deleteUser() {
		fetch("http://localhost:8080/user/" + userId, {
			method: "DELETE",
		}).then((res) => {
			if (res.status < 400) {
				setDeleteCard(false);
				history.push("/auth");
				authCtx.logout();
			}
		});
	}
	return (
		<div className={classes.content}>
			<div className={classes.user}>
				{!edit && (
					<>
						<h1>Username:</h1>
						<h2>{username}</h2>
						<h1>Name:</h1>
						<h2>{fullName}</h2>
						<h1>Email:</h1>
						<p>{email}</p>
						<button onClick={editStateHandler}>Edit</button>
						<button
							onClick={() => {
								setDeleteCard(true);
							}}
						>
							Delete
						</button>
					</>
				)}
				{edit && (
					<form onSubmit={editSubmitHandler}>
						<h2>Full name:</h2>
						<input
							type="text"
							id="fullName"
							placeholder={fullName}
							onChange={(e) => setFullName(e.target.value)}
						/>
						<h2>Email:</h2>
						<input
							type="email"
							id="email"
							placeholder={email}
							onChange={(e) => setEmail(e.target.value)}
						/>
						<h2>Username:</h2>
						<input
							type="text"
							id="username"
							placeholder={username}
							onChange={(e) => setUsername(e.target.value)}
						/>
						<h2>Password:</h2>
						<input
							type="password"
							id="password"
							onChange={(e) => setPassword(e.target.value)}
						/>
						<button type="submit">Edit</button>
						<button
							onClick={() => {
								setEdit(false);
							}}
						>
							Cancle
						</button>
					</form>
				)}
			</div>

			{error && (
				<MessageModal
					title="Something went wrong"
					message="Check the date you enter, have on mind that username must be unique."
					onCancle={closeError}
				/>
			)}
			{error && <Backdrop onCancle={closeError} />}
			{message && (
				<MessageModal
					title="Edit"
					message="You edit your datas so you need to log in again."
					onCancle={closeMessage}
				/>
			)}
			{message && <Backdrop onCancle={closeMessage} />}
			{deleteCard && (
				<DeleteCard
					title="Delete"
					message="Are you sure you want to delete your account?."
					onCancle={closeDeleteCard}
					onDelete={deleteUser}
				/>
			)}
			{deleteCard && <Backdrop onCancle={closeDeleteCard} />}
		</div>
	);
}

export default MyProfilePage;
