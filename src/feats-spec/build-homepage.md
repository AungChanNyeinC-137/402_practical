Build a single-page React application using Vite, Tailwind CSS, and shadcn/ui components based on the following specifications:

### Project Requirements & Features
1. **Source of Truth**: 
   - Import all topic data from `constants.ts`.
   - The data follows a structure where each topic item contains an `id`, `title`, `description`, and an array of `files` with a `filename` and static `path` (located in `/public/Topics/...`).

2. **Homepage Layout**:
   - The main screen directly displays a clean, vertical list/accordion of short topic titles/cards based on the items in `constants.ts`.

3. **Expandable Topic Items**:
   - Clicking on a topic title/card expands or toggles a nested view showing its short description and the list of associated files (e.g., `activity_main.xml`, `MainActivity.java`).
   - You can use shadcn `Accordion` or collapsible `Card` components for a clean accordion effect.

4. **File List & Download Action**:
   - Each file in the list shows its `filename` alongside a Download icon button positioned on the right side.
   - Clicking the Download button downloads the file directly to the user's local machine with its original filename using a standard HTML download link (`<a href={file.path} download={file.filename}>`) or equivalent handler.
   - No copy-to-clipboard logic or text contents in memory needed.

### Design Guidelines
- Modern, clean UI styled with Tailwind CSS and styled using shadcn/ui components (e.g., `Card`, `Accordion`, `Button`, or `Lucide` icons like `Download`, `FileText`, `ChevronDown`).
- Responsive and accessible design suitable for desktop and mobile viewports.