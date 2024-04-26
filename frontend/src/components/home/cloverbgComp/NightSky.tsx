import React from 'react';
import styles from '@/assets/clover/star/scss/NightSky.module.scss';

const NightSky: React.FC = () => {
  return (
    <div className={styles.night}>
      {Array.from({ length: 20 }).map((_, i) => (
        <div key={i} className={styles.shooting_star}></div>
      ))}
    </div>
  );
};

export default NightSky;
