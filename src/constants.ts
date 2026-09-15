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
    id: "practical-10-alarm",
    title: "Practical 10 Alarm",
    description: "Alarm manager implementation with activity layout and main class.",
    files: [
      {
        id: "p10-alarm-activity-main",
        filename: "activity_main.xml",
        path: "/Topics/Practical 10 Alarm/activity_main.xml",
      },
      {
        id: "p10-alarm-main-activity",
        filename: "MainActivity.java",
        path: "/Topics/Practical 10 Alarm/MainActivity.java",
      },
      {
        id: "p10-alarm-strings",
        filename: "strings.xml",
        path: "/Topics/Practical 10 Alarm/strings.xml",
      },
    ],
  },
  {
    id: "practical-10-scheduler",
    title: "Practical 10 Scheduler",
    description: "Job scheduler implementation with alarm receiver and activity layout.",
    files: [
      {
        id: "p10-scheduler-activity-main",
        filename: "activity_main.xml",
        path: "/Topics/Practical 10 Scheduler/activity_main.xml",
      },
      {
        id: "p10-scheduler-alarm-receiver",
        filename: "AlarmReceiver.java",
        path: "/Topics/Practical 10 Scheduler/AlarmReceiver.java",
      },
      {
        id: "p10-scheduler-main-activity",
        filename: "MainActivity.java",
        path: "/Topics/Practical 10 Scheduler/MainActivity.java",
      },
      {
        id: "p10-scheduler-strings",
        filename: "strings.xml",
        path: "/Topics/Practical 10 Scheduler/strings.xml",
      },
    ],
  },
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
    id: "open-mp",
    title: "OpenMP",
    description: "OpenMP implementation for parallel programming.",
    files: [
      {
        id: "reduction",
        filename: "reduction.c",
        path: "/Topics/OpenMP/reduction.c",
      },
      {
        id: "sections",
        filename: "sections.c",
        path: "/Topics/OpenMP/sections.c",
      },
      {
        id: "scheduling",
        filename: "scheduling.c",
        path: "/Topics/OpenMP/scheduling.c",
      }
    ],
  },

  {
    id: "mpi",
    title: "MPI",
    description: "MPI implementation for parallel programming.",
    files: [
      {
        id: "reduction",
        filename: "No1.cpp",
        path: "/Topics/MPI/No1.cpp",
      },
      {
        id: "sections",
        filename: "No2.cpp",
        path: "/Topics/MPI/No2.cpp",
      },
     
    ],
  },
];