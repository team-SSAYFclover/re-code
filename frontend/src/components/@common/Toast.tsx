import { CSSProperties, ReactNode } from 'react';
import { Flip, ToastOptions, toast } from 'react-toastify';

const defaultToastOption: ToastOptions = {
  autoClose: 2000,
  hideProgressBar: true,
  pauseOnHover: false,
  closeButton: false,
  delay: 500,
  transition: Flip,
  icon: false,
};

const commonStyle: CSSProperties = {
  position: 'fixed',
  top: '25px',
  left: '0px',
  right: '0px',
  borderRadius: '10px',
  color: 'white',
  textAlign: 'center',
  margin: 'auto',
  width: '400px',
  height: 'auto',
  padding: '0px 10px',
  fontSize: '15px',
  fontFamily: 'Pretendard-Regular',
};

const Toast = {
  info: (message: ReactNode) => {
    toast.info(message, {
      ...defaultToastOption,
      style: {
        ...commonStyle,
        backgroundColor: 'rgb(73, 73, 73)',
      },
    });
  },
  success: (message: ReactNode) => {
    toast.success(message, {
      ...defaultToastOption,
      style: {
        ...commonStyle,
        backgroundColor: '#ecfffa',
        color: '#53edc7',
      },
    });
  },
  error: (message: ReactNode) => {
    toast.error(message, {
      ...defaultToastOption,
      style: {
        ...commonStyle,
        backgroundColor: 'rgb(255, 114, 114)',
        color: 'white',
      },
    });
  },
};

export default Toast;
