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
];