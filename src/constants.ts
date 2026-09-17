// constant.ts

export interface FileItem {
  id: string;
  filename: string;
  path: string; // Direct URL path relative to public/
}

export interface TopicItem {
  id: string;
  title: string;
  description: string;
  files: FileItem[];
}

export const TOPICS_DATA: TopicItem[] = [
 
  {
    id: "student-registration-form",
    title: "Student Registration Form",
    description: "Student registration form layout and implementation with major options and clear action.",
    files: [
      {
        id: "student-reg-activity-main",
        filename: "activity_main.xml",
        path: "/Topics/Student Registration Form App/activity_main.xml",
      },
      {
        id: "student-reg-main-activity",
        filename: "MainActivity.java",
        path: "/Topics/Student Registration Form App/MainActivity.java",
      },
    ],
  },
  {
    id: "hotel-booking-system",
    title: "Hotel Booking System",
    description: "Hotel booking system UI layout and implementation supporting room type selection and multi-floor choices.",
    files: [
      {
        id: "hotel-booking-activity-main",
        filename: "activity_main.xml",
        path: "/Topics/Hotel Booking System App/activity_main.xml",
      },
      {
        id: "hotel-booking-main-activity",
        filename: "MainActivity.java",
        path: "/Topics/Hotel Booking System App/MainActivity.java",
      },
    ],
  },
  {
    id: "bmi-calculator",
    title: "BMI Calculator",
    description: "BMI calculator interface and logic supporting weight and height decimal inputs.",
    files: [
      {
        id: "bmi-calc-activity-main",
        filename: "activity_main.xml",
        path: "/Topics/BMI Calculator App/activity_main.xml",
      },
      {
        id: "bmi-calc-main-activity",
        filename: "MainActivity.java",
        path: "/Topics/BMI Calculator App/MainActivity.java",
      },
    ],
  },
  {
    id: "two-activities",
    title: "Two Activities",
    description: "Two Activities layout and implementation demonstrating intent messaging and result handling between screens.",
    files: [
      {
        id: "two-activities-activity-main",
        filename: "activity_main.xml",
        path: "/Topics/Two Activities/activity_main.xml",
      },
      {
        id: "two-activities-activity-second",
        filename: "activity_second.xml",
        path: "/Topics/Two Activities/activity_second.xml",
      },
      {
        id: "two-activities-colors",
        filename: "colors.xml",
        path: "/Topics/Two Activities/colors.xml",
      },
      {
        id: "two-activities-dimens",
        filename: "dimens.xml",
        path: "/Topics/Two Activities/dimens.xml",
      },
      {
        id: "two-activities-main-activity",
        filename: "MainActivity.java",
        path: "/Topics/Two Activities/MainActivity.java",
      },
      {
        id: "two-activities-second-activity",
        filename: "SecondActivity.java",
        path: "/Topics/Two Activities/SecondActivity.java",
      },
      {
        id: "two-activities-strings",
        filename: "strings.xml",
        path: "/Topics/Two Activities/strings.xml",
      },]}
  
];