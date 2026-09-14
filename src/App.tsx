import { Button } from '@/components/ui/button'
import { Card } from '@/components/ui/card'
import { TOPICS_DATA } from '@/constants'
import { ChevronDown, Download, FileText } from 'lucide-react'

function App() {
  return (
    <main className="min-h-screen bg-slate-50 px-4 py-10 sm:px-6 sm:py-16">
      <div className="mx-auto max-w-3xl">
    

        <section aria-label="Practical topics" className="space-y-3">
          {TOPICS_DATA.map((topic) => (
            <Card key={topic.id} className="overflow-hidden">
              <details className="group">
                <summary className="flex cursor-pointer list-none items-center justify-between gap-4 px-5 py-4 marker:content-none hover:bg-slate-50 focus-visible:outline-2 focus-visible:outline-offset-[-2px] focus-visible:outline-primary sm:px-6">
                  <span className="text-base font-semibold sm:text-lg">{topic.title}</span>
                  <ChevronDown aria-hidden="true" className="size-5 shrink-0 text-muted-foreground transition-transform duration-200 group-open:rotate-180" />
                </summary>
                <div className="border-t border-border px-5 py-5 sm:px-6">
                  <p className="mb-5 text-sm leading-6 text-muted-foreground">{topic.description}</p>
                  <ul aria-label={`${topic.title} files`} className="space-y-2">
                    {topic.files.map((file) => (
                      <li key={file.id} className="flex items-center gap-3 rounded-lg border border-border bg-slate-50/70 px-3 py-2.5">
                        <FileText aria-hidden="true" className="size-5 text-primary" />
                        <span className="min-w-0 flex-1 truncate font-mono text-sm">{file.filename}</span>
                        <Button href={file.path} download={file.filename} aria-label={`Download ${file.filename}`} title={`Download ${file.filename}`}><Download aria-hidden="true" className="size-5" /></Button>
                      </li>
                    ))}
                  </ul>
                </div>
              </details>
            </Card>
          ))}
        </section>
      </div>
    </main>
  )
}

export default App
