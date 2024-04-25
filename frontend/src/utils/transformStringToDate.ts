export const transformStringToDate = (str: string) => {
  const [date, time] = str.replace('Z', '').split('T');
  const [year, month, day] = date.split('-').map(Number);
  const [hour, min, sec] = time.split(':').map(Number);

  return {
    year,
    month,
    day,
    hour,
    min,
    sec,
  };
};
