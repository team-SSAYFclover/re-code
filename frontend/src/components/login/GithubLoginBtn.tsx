import { FaGithub } from 'react-icons/fa';

const GithubLoginBtn = () => {
  const handleLogin = () => {
    window.location.href = `${import.meta.env.VITE_BASE_URL}/api/oauth2/authorization/github`;
  };

  return (
    <button
      className="w-56 h-12 bg-black text-white flex justify-around items-center rounded-md"
      onClick={handleLogin}
    >
      <FaGithub size={24} />
      <span>Github으로 시작하기</span>
    </button>
  );
};

export default GithubLoginBtn;
